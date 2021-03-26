package com.gsm.chwijuntime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gsm.chwijuntime.model.tagmapping.EmploymentAnnouncementTag;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberIdx;

    @Column(nullable = false)
    private String memberEmail;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String memberPassword;

    @Enumerated(EnumType.STRING)
    private MemberGender memberGender;

    @Column(nullable = false, length = 4)
    private String memberClassNumber;

    @Column(length = 13)
    private String memberPhoneNumber;

    private String memberHomeAddress;

    private LocalDateTime memberCreated;

    private LocalDateTime memberDeleted;

    @Enumerated(EnumType.STRING)
    private MemberDelflag memberDelflag;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    // ===================== UserDetails ========================== //
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.memberPassword;
    }

    @Override
    public String getUsername() {
        return this.memberEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // ==================== 비즈니스 로직 ==================== //
    public void Change_Email(String email){
        this.memberEmail = email;
    }
}
