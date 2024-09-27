package br.com.cbyk.contas_pagar.adapters.out.repository.mapper;

import br.com.cbyk.contas_pagar.adapters.out.repository.entity.ContaEntity;
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
public class ContaMapperImpl implements ContaMapper {

    @Override
    public ContaEntity toContaEntity(Conta conta) {
        if ( conta == null ) {
            return null;
        }

        ContaEntity contaEntity = new ContaEntity();

        contaEntity.setId( conta.getId() );
        contaEntity.setDataVencimento( conta.getDataVencimento() );
        contaEntity.setDataPagamento( conta.getDataPagamento() );
        contaEntity.setValor( conta.getValor() );
        contaEntity.setDescricao( conta.getDescricao() );
        contaEntity.setSituacao( conta.getSituacao() );

        return contaEntity;
    }

    @Override
    public Conta toConta(ContaEntity contaEntity) {
        if ( contaEntity == null ) {
            return null;
        }

        Conta conta = new Conta();

        conta.setDataVencimento( contaEntity.getDataVencimento() );
        conta.setDataPagamento( contaEntity.getDataPagamento() );
        conta.setValor( contaEntity.getValor() );
        conta.setDescricao( contaEntity.getDescricao() );
        conta.setSituacao( contaEntity.getSituacao() );
        conta.setId( contaEntity.getId() );

        return conta;
    }

    @Override
    public List<Conta> toConta(List<ContaEntity> contaEntity) {
        if ( contaEntity == null ) {
            return null;
        }

        List<Conta> list = new ArrayList<Conta>( contaEntity.size() );
        for ( ContaEntity contaEntity1 : contaEntity ) {
            list.add( toConta( contaEntity1 ) );
        }

        return list;
    }

    @Override
    public List<ContaEntity> toContasEntity(List<Conta> contas) {
        if ( contas == null ) {
            return null;
        }

        List<ContaEntity> list = new ArrayList<ContaEntity>( contas.size() );
        for ( Conta conta : contas ) {
            list.add( toContaEntity( conta ) );
        }

        return list;
    }
}
