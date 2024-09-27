package br.com.cbyk.contas_pagar.application.core.utils;

import org.springframework.web.multipart.MultipartFile;

public class ExcelUtil {

    private static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static String[] headerConta() {
        final String[] HEADERS = {
            "DATA_PAGAMENTO",
            "DATA_VENCIMENTO",
            "VALOR",
            "DESCRICAO",
            "SITUACAO"
        };
        return HEADERS;
    }
}
