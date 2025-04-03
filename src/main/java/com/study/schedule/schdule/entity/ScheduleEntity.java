package com.study.schedule.schdule.entity;

import com.study.schedule.others.entity.BaseEntity;
import com.study.schedule.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "schedule")
@NoArgsConstructor
public class ScheduleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false, unique = true)
//    private String user;

    @Column(nullable = false)
    private String title;

    private String contents;

    // 단반향 받는 쪽
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntityId;

    public ScheduleEntity(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void setUseriD(UserEntity userEntityId) {
        this.userEntityId = userEntityId;
    }

    public void updateTitleAndContents(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
