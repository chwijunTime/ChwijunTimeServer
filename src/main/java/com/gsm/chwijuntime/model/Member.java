package com.gsm.chwijuntime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
    private MemberGender memberGender;

    @Column(nullable = false, length = 4)
    private String MemberClassNumber;

    @Column(nullable = false, length = 13)
    private String MemberPhoneNumber;

    private String MemberHomeAddress;

    private LocalDateTime MemberCreated;

    private LocalDateTime MemberDeleted;

    @Enumerated(EnumType.STRING)
    private MemberDelflag memberDelflag;

    @OneToMany(mappedBy = "member")
    private List<Notice> notices = new ArrayList<>();
}
