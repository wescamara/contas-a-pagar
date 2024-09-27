package br.com.cbyk.contas_pagar.adapters.in.controller.mapper;

import br.com.cbyk.contas_pagar.adapters.in.controller.response.ContaInvalidResponse;
import br.com.cbyk.contas_pagar.application.core.domain.ContaInvalid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-27T14:51:16-0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class ContaInvalidMapperResponseImpl implements ContaInvalidMapperResponse {

    @Override
    public List<ContaInvalidResponse> toContaInvalid(List<ContaInvalid> contas) {
        if ( contas == null ) {
            return null;
        }

        List<ContaInvalidResponse> list = new ArrayList<ContaInvalidResponse>( contas.size() );
        for ( ContaInvalid contaInvalid : contas ) {
            list.add( contaInvalidToContaInvalidResponse( contaInvalid ) );
        }

        return list;
    }

    protected ContaInvalidResponse contaInvalidToContaInvalidResponse(ContaInvalid contaInvalid) {
        if ( contaInvalid == null ) {
            return null;
        }

        ContaInvalidResponse contaInvalidResponse = new ContaInvalidResponse();

        List<Map<String, String>> list = contaInvalid.getReasons();
        if ( list != null ) {
            contaInvalidResponse.setReasons( new ArrayList<Map<String, String>>( list ) );
        }
        if ( contaInvalid.getRow() != null ) {
            contaInvalidResponse.setRow( contaInvalid.getRow() );
        }

        return contaInvalidResponse;
    }
}
