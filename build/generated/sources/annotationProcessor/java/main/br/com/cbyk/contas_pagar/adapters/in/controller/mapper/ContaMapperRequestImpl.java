package br.com.cbyk.contas_pagar.adapters.in.controller.mapper;

import br.com.cbyk.contas_pagar.adapters.in.controller.request.ContaRequest;
import br.com.cbyk.contas_pagar.application.core.domain.Conta;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-27T14:51:16-0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class ContaMapperRequestImpl implements ContaMapperRequest {

    @Override
    public Conta toConta(ContaRequest request) {
        if ( request == null ) {
            return null;
        }

        Conta conta = new Conta();

        conta.setDataVencimento( request.getDataVencimento() );
        conta.setDataPagamento( request.getDataPagamento() );
        conta.setValor( request.getValor() );
        conta.setDescricao( request.getDescricao() );
        conta.setSituacao( request.getSituacao() );

        return conta;
    }
}
