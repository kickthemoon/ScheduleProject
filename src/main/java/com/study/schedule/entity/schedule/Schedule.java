package com.study.schedule.entity.schedule;

import com.study.schedule.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

//    @Column(nullable = false, unique = true)
//    private String user;

    @Column(nullable = false)
    private String title;

    private String contents;

    // 단반향 받는 쪽
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
