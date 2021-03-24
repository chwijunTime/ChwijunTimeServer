package com.gsm.chwijuntime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gsm.chwijuntime.model.tagmapping.EmploymentAnnouncementTag;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MemberIdx;

    @Column(nullable = false)
    private String MemberEmail;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String MemberPassword;

    @Enumerated(EnumType.STRING)
    private MemberGender MemberGender;

    @Column(nullable = false, length = 4)
    private String MemberClassNumber;

    @Column(nullable = false, length = 13)
    private String MemberPhoneNumber;

    private String MemberHomeAddress;

    private LocalDateTime MemberCreated;

    private LocalDateTime MemberDeleted;

    @Enumerated(EnumType.STRING)
    private MemberDelflag memberDelflag;

    // ================= 연관관계 노예 ================ //
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<ApplicationEmployment> applicationEmployments = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<TipsStorage> tipsStorages = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<CompanyReview> companyReviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberResume> memberResumes = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<EmploymentConfirmation> employmentConfirmations = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberPortfolio> memberPortfolios = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<ContractingCompany> contractingCompanies = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<ConsultingAdmin> consultingAdmins = new ArrayList<>();

    // ============== 태그 매핑(연관관계 노예) -=============== //
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberTag> memberTags = new ArrayList<>();
}
