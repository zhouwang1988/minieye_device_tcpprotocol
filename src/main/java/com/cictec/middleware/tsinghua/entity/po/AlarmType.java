package com.cictec.middleware.tsinghua.entity.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "t_sys_datadict")
@Data
@ToString
public class AlarmType {
    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private String value;

    @Column(name = "display")
    private String display;

    @Column(name = "sort")
    private String sort;

    @Column(name = "isvalid")
    private String isvalid;

   }