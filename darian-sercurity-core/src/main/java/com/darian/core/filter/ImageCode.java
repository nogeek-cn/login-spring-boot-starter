package com.darian.core.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ImageCode {
    /***
     *
     */
    public BufferedImage image;

    /***
     *
     */
    private String code;
    /***
     *
     */
    private LocalDateTime expireTime;

    /***
     *
     * 构造函数
     * @param image
     * @param code
     * @param expireIn  过期的秒数
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {

        return LocalDateTime.now().isAfter(expireTime);
    }
}
