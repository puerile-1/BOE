package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PubAnnouncement {
    private static final long serialVersionUID = 1L;
    @TableId(value = "pub_announcement_id", type = IdType.AUTO)
    private Integer pubAnnouncementId;

    private String pubAnnouncementContent;

    private String pubAnnouncementTextcolor;

    private Integer pubAnnouncementTextsize;

    private String pubAnnouncementBgcolor;

    private Integer pubAnnouncementBghigh;

    private String pubAnnouncementTexthigh;

    private String pubAnnouncementPlayspeed;
}
