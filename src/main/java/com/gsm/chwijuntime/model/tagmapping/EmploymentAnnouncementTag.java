package com.gsm.chwijuntime.model.tagmapping;

import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.TipsStorage;
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
public class EmploymentAnnouncementTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmploymentAnnouncementTagIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TagIdx")
    private Tag tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ApplicationEmploymentIdx")
    private EmploymentAnnouncement employmentAnnouncement;

}
