package com.gsm.chwijuntime.model.tagmapping;

import com.gsm.chwijuntime.model.EmploymentConfirmation;
import com.gsm.chwijuntime.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmploymentConfirmationTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmploymentConfirmationTagIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TagIdx")
    private Tag tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EmploymentConfirmationIdx")
    private EmploymentConfirmation employmentConfirmation;

}
