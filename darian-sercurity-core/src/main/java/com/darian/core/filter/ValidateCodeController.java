package com.darian.core.filter;

import com.darian.core.exception.CustomException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@RestController
public class ValidateCodeController {
    public static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @GetMapping(value = "/code/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity createCodeImage(HttpServletRequest request, HttpServletResponse response) {
        ImageCode imageCode = createImageCode(request);

        sessionStrategy.setAttribute(new ServletWebRequest(request),
                SESSION_KEY_IMAGE_CODE, imageCode);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(imageCode.getImage(), "jpeg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return ResponseEntity.ok(imageInByte);
        } catch (IOException e) {
            throw CustomException.generatorRuntimeException("图片验证码生成错误");
        }
    }

    public ImageCode createImageCode(HttpServletRequest request) {
//        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
//                securityProperties.getCode().getImage().getWidth());
//        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
//                securityProperties.getCode().getImage().getHeight());
        int width = 100;
        int height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        // 图片验证码的长度
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageCode(image, sRand, 60);

    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
