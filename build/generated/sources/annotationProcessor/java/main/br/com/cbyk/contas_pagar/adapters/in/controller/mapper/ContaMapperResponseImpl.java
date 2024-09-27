package br.com.cbyk.contas_pagar.adapters.in.controller.mapper;

import br.com.cbyk.contas_pagar.adapters.in.controller.response.ContaResponse;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-27T14:51:16-0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class ContaMapperResponseImpl implements ContaMapperResponse {

    @Override
    public ContaResponse toContaResponse(Conta conta) {
        if ( conta == null ) {
            return null;
        }

        ContaResponse contaResponse = new ContaResponse();

        contaResponse.setId( conta.getId() );
        contaResponse.setDataVencimento( conta.getDataVencimento() );
        contaResponse.setDataPagamento( conta.getDataPagamento() );
        contaResponse.setValor( conta.getValor() );
        contaResponse.setDescricao( conta.getDescricao() );
        contaResponse.setSituacao( conta.getSituacao() );

        return contaResponse;
    }

    @Override
    public List<ContaResponse> toContaResponse(List<Conta> contas) {
        if ( contas == null ) {
            return null;
        }

        List<ContaResponse> list = new ArrayList<ContaResponse>( contas.size() );
        for ( Conta conta : contas ) {
            list.add( toContaResponse( conta ) );
        }

        return list;
    }
}
