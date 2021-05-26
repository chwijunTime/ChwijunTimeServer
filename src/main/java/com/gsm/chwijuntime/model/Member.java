package com.gsm.chwijuntime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member extends BaseTimeEntity implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberIdx;

    @Column(nullable = false)
    private String memberEmail;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 100)
    private String memberPassword;

    @Column(nullable = false, length = 4)
    private String memberClassNumber;

    @Column(length = 13)
    private String memberPhoneNumber;

    @Column
    private String memberETC;

    private LocalDateTime memberCreated;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    // ===================== UserDetails ========================== //
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword() {
        return this.memberPassword;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.memberEmail;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // ==================== 비즈니스 로직 ==================== //
    public void Change_Email(String email){
        this.memberEmail = email;
    }

    public String String_Role(Member member){
        Iterator<? extends GrantedAuthority> authorityIterator = member.getAuthorities().iterator();
        return authorityIterator.next().toString();
    }

    public void change_profile(String memberPhoneNumber, String memberETC){
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberETC = memberETC;
    }

    public void change_password(String password){
        this.memberPassword = password;
    }
}