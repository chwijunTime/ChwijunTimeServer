package com.gsm.chwijuntime.model.tagmapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContractingCompanyTag is a Querydsl query type for ContractingCompanyTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContractingCompanyTag extends EntityPathBase<ContractingCompanyTag> {

    private static final long serialVersionUID = -1648176185L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContractingCompanyTag contractingCompanyTag = new QContractingCompanyTag("contractingCompanyTag");

    public final com.gsm.chwijuntime.model.QContractingCompany contractingCompany;

    public final NumberPath<Long> ContractingCompanyTagIdx = createNumber("ContractingCompanyTagIdx", Long.class);

    public final com.gsm.chwijuntime.model.QTag tag;

    public QContractingCompanyTag(String variable) {
        this(ContractingCompanyTag.class, forVariable(variable), INITS);
    }

    public QContractingCompanyTag(Path<? extends ContractingCompanyTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContractingCompanyTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContractingCompanyTag(PathMetadata metadata, PathInits inits) {
        this(ContractingCompanyTag.class, metadata, inits);
    }

    public QContractingCompanyTag(Class<? extends ContractingCompanyTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contractingCompany = inits.isInitialized("contractingCompany") ? new com.gsm.chwijuntime.model.QContractingCompany(forProperty("contractingCompany"), inits.get("contractingCompany")) : null;
        this.tag = inits.isInitialized("tag") ? new com.gsm.chwijuntime.model.QTag(forProperty("tag")) : null;
    }

}

