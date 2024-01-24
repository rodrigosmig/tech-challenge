package br.com.tech.challenge.sistemapedido.infrastructure.service;

import br.com.tech.challenge.sistemapedido.domain.ItemPedido;
import br.com.tech.challenge.sistemapedido.domain.Pedido;
import br.com.tech.challenge.sistemapedido.domain.exception.SistemaPedidosAPIException;
import br.com.tech.challenge.sistemapedido.infrastructure.integration.rest.mercadopago.GerarCodigoQrRequest;
import br.com.tech.challenge.sistemapedido.infrastructure.integration.rest.mercadopago.MercadoPagoHttpClient;
import br.com.tech.challenge.sistemapedido.infrastructure.integration.rest.qrcodeapi.QrCodeHttpClient;
import br.com.tech.challenge.sistemapedido.infrastructure.integration.transfer.ItemTO;
import br.com.tech.challenge.sistemapedido.usecase.service.GerarPagamentoService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GerarPagamentoServiceImpl implements GerarPagamentoService {
    private final MercadoPagoHttpClient mercadopagoHttpClient;
    private final QrCodeHttpClient qrCodeHttpClient;
    private final Long HORAS = 2L;

    @Override
    public File gerarQrCode(Pedido pedido) {
        var request = obterRequest(pedido);
        try {
            var gerarQrDataResponse = mercadopagoHttpClient.gerarQrData(request);
            var qrCodeData = gerarQrDataResponse.getBody();

            if (Objects.isNull(qrCodeData) || Objects.isNull(qrCodeData.qrData())) {
                throw new SistemaPedidosAPIException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível obter os dados para gerar o QR-Code");
            }

            var gerarQrCodeResponse = qrCodeHttpClient.gerarQrCode("300x300", qrCodeData.qrData());
            var qrCodeImage = gerarQrCodeResponse.getBody();

            if (Objects.isNull(qrCodeImage)) {
                throw new SistemaPedidosAPIException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível obter a imagem do QR-Code");
            }

            ByteArrayInputStream bis = new ByteArrayInputStream(qrCodeImage);
            BufferedImage bufferedImage = ImageIO.read(bis);
            String fileName = "qr-codes/qrcode-pedido-" + pedido.getId() + ".png";
            var file = new File(fileName);
            ImageIO.write(bufferedImage, "png", file);

            return file;
        } catch (FeignException ex) {
            //TODO melhora a captura da excecao
            var teste = ex.responseBody();
          throw new SistemaPedidosAPIException(HttpStatus.valueOf(ex.status()), ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private GerarCodigoQrRequest obterRequest(Pedido pedido) {
        var items = this.obterItems(pedido.getItens());

        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime expiration = new Date().toInstant()
                .atZone(zone)
                .toLocalDateTime().plusHours(HORAS).atZone(zone);

        return GerarCodigoQrRequest.builder()
                .description("Pedido para pagamento")
                .externalReference(pedido.getId().toString())
                .expirationDate(expiration)
                .notificationUrl("https://www.yourserver.com/notifications")
                .title("Restaurante Fiap")
                .totalAmount(pedido.getTotal().getPreco())
                .items(items)
                .build();
    }

    private List<ItemTO> obterItems(List<ItemPedido> itemsPedido) {
        return itemsPedido.stream()
                .map(itemPedido -> ItemTO.builder()
                        .title(itemPedido.getProduto().getNome().toString())
                        .description(itemPedido.getProduto().getDescricao().toString())
                        .unitPrice(itemPedido.getProduto().getPreco().getPreco())
                        .quantity(itemPedido.getQuantidade().getQuantidade())
                        .unitMeasure("unit")
                        .totalAmount(itemPedido.getProduto().getPreco().getPreco().multiply(new BigDecimal(itemPedido.getQuantidade().getQuantidade())))
                        .build())
                .toList();
    }
}
