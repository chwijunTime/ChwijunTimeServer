package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContractingCompany is a Querydsl query type for ContractingCompany
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContractingCompany extends EntityPathBase<ContractingCompany> {

    private static final long serialVersionUID = 1409020609L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContractingCompany contractingCompany = new QContractingCompany("contractingCompany");

    public final StringPath contractingArea = createString("contractingArea");

    public final StringPath contractingBusinessAreas = createString("contractingBusinessAreas");

    public final StringPath contractingCompanyAboutUs = createString("contractingCompanyAboutUs");

    public final StringPath contractingCompanyAddress = createString("contractingCompanyAddress");

    public final StringPath contractingCompanyAverageAnnualSalary = createString("contractingCompanyAverageAnnualSalary");

    public final NumberPath<Long> contractingCompanyIdx = createNumber("contractingCompanyIdx", Long.class);

    public final StringPath contractingCompanyName = createString("contractingCompanyName");

    public final QMember member;

    public QContractingCompany(String variable) {
        this(ContractingCompany.class, forVariable(variable), INITS);
    }

    public QContractingCompany(Path<? extends ContractingCompany> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContractingCompany(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContractingCompany(PathMetadata metadata, PathInits inits) {
        this(ContractingCompany.class, metadata, inits);
    }

    public QContractingCompany(Class<? extends ContractingCompany> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

