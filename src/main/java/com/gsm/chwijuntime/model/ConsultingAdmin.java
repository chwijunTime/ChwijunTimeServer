package com.gsm.chwijuntime.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 상담 등록 테이블
public class ConsultingAdmin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultingIdx;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime applicationDate;

    @Enumerated(EnumType.STRING)
    private ConsultingStatus consultingStatus;

    // ================ 외래키(연관관계 주인) ================= //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    public void changeConsultingStatus(){
        this.consultingStatus = ConsultingStatus.Apply;
    }
}
