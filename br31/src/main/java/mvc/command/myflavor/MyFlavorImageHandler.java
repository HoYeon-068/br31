package mvc.command.myflavor;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import mvc.domain.myflavor.FlavorDTO;
import mvc.domain.myflavor.MyFlavorResultDTO;

public class MyFlavorImageHandler implements CommandHandler {

    // ✅ 원본 카드 이미지 기준
    private static final int W = 700;
    private static final int H = 700;

    // ✅ 플레이버 썸네일(원) 크기
    private static final int CIRCLE = 140;

    // ✅ 행 간격 / 텍스트 간격
    private static final int NAME_GAP_Y = 30;
    private static final int ROW_GAP_Y = 85;

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        if (!"GET".equalsIgnoreCase(req.getMethod())) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }

        String seq = req.getParameter("seq");
        if (seq == null || seq.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        Object obj = session.getAttribute("MYFLAVOR_" + seq);
        if (!(obj instanceof MyFlavorResultDTO)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        MyFlavorResultDTO dto = (MyFlavorResultDTO) obj;

        List<FlavorDTO> flavors = dto.getFlavors() == null ? new ArrayList<>() : dto.getFlavors();
        int count = Math.min(6, flavors.size()); // 최대 6개만

        BufferedImage canvas = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = canvas.createGraphics();

        // 품질
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // 배경
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, W, H);

        // =========================
        // 1) HEADER (원본 느낌)
        // =========================
        // 상단 설명
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.setColor(new Color(60, 60, 60));
        g.drawString("마이 플레이버 리스트", 40, 55);

        // "3가지 맛(파인트)" 같은 강조 텍스트
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.setColor(new Color(255, 110, 140));
        g.drawString(sizeLabelKorean(dto.getSize(), count), 250, 55);

        // 메인 타이틀
        g.setFont(new Font("SansSerif", Font.BOLD, 56));
        g.setColor(Color.BLACK);
        drawCenteredTextMaxLines(g, safe(dto.getTitle()), W / 2, 125, 620, 1); // 1줄만, 길면 말줄임

        // 점선
        g.setColor(new Color(225, 225, 225));
        float[] dash = { 6f };
        g.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1f, dash, 0));
        g.drawLine(40, 180, W - 40, 180);

        // 태그 pill (원본에 있던 작은 캡슐)
        String badge = safe(dto.getBadgeTag());
        if (!badge.isBlank()) {
            drawPill(g, 40, 205, badge, new Color(245, 245, 245), new Color(120, 120, 120));
        }

        // =========================
        // 2) FLAVORS (3~6 레이아웃)
        // =========================
        // 배치 규칙:
        // 3 : 1줄(3)
        // 4 : 1줄(4) (원본 사이트는 3이 예시지만 4는 1줄이 제일 예쁨)
        // 5 : 3 + 2
        // 6 : 3 + 3
        // (원본 느낌대로 "원들 중심 정렬"이 핵심)

        int startY;
        if (count <= 4) {
            startY = 285;
        } else {
            startY = 255;
        }

        if (count == 0) {
            g.setFont(new Font("SansSerif", Font.PLAIN, 18));
            g.setColor(new Color(160, 160, 160));
            drawCenteredText(g, "선택된 플레이버가 없습니다.", W / 2, 360);
        } else if (count <= 4) {
            // 1줄 배치
            int gapX = gapXForCols(count);
            drawRow(g, req, flavors.subList(0, count), startY, count, gapX);
        } else if (count == 5) {
            // 3 + 2
            int gapXTop = gapXForCols(3);
            int gapXBottom = gapXForCols(2);

            drawRow(g, req, flavors.subList(0, 3), startY, 3, gapXTop);
            drawRow(g, req, flavors.subList(3, 5), startY + CIRCLE + ROW_GAP_Y, 2, gapXBottom);
        } else { // 6
            int gapX = gapXForCols(3);
            drawRow(g, req, flavors.subList(0, 3), startY, 3, gapX);
            drawRow(g, req, flavors.subList(3, 6), startY + CIRCLE + ROW_GAP_Y, 3, gapX);
        }

        // =========================
        // 3) FOOTER (By...)
        // =========================
        g.setFont(new Font("SansSerif", Font.BOLD, 18));
        g.setColor(new Color(130, 130, 130));
        String by = "By " + maskWriter(dto.getWriter());
        // 원본은 우하단 쪽
        g.drawString(by, W - 180, 520);

        g.dispose();

        // 응답
        resp.setContentType("image/png");
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache");

        try (ServletOutputStream os = resp.getOutputStream()) {
            ImageIO.write(canvas, "png", os);
            os.flush();
        }
        return null;
    }

    // =========================
    // Row Drawing
    // =========================
    private static void drawRow(Graphics2D g, HttpServletRequest req, List<FlavorDTO> row, int y, int cols, int gapX) {
        int totalW = cols * CIRCLE + (cols - 1) * gapX;
        int startX = (W - totalW) / 2;

        for (int i = 0; i < row.size(); i++) {
            int x = startX + i * (CIRCLE + gapX);

            FlavorDTO f = row.get(i);

            // 원형 이미지
            drawCircleImage(g, req, f.getImgPath(), x, y, CIRCLE);

            // 맛 이름 (2줄 래핑 + 마지막 줄 말줄임)
            g.setFont(new Font("SansSerif", Font.BOLD, 18));
            g.setColor(Color.BLACK);
            int textY = y + CIRCLE + NAME_GAP_Y;
            drawCenteredTextMaxLines(g, safe(f.getName()), x + CIRCLE / 2, textY, CIRCLE + 30, 2);
        }
    }

    private static int gapXForCols(int cols) {
        // 원본 느낌: 3개일 때 gap이 가장 자연스러움.
        // 4개는 너무 촘촘해지지 않게 gap을 줄임.
        if (cols == 2) return 90;
        if (cols == 3) return 40;
        if (cols == 4) return 18;
        return 40;
    }

    // =========================
    // Circle Image
    // =========================
    private static void drawCircleImage(Graphics2D g, HttpServletRequest req, String imgPath, int x, int y, int size) {
        BufferedImage img = loadLocalImage(req, imgPath);

        // 바깥 흰 테두리(원본 느낌 살짝)
        g.setColor(Color.WHITE);
        g.fillOval(x - 6, y - 6, size + 12, size + 12);

        Shape oldClip = g.getClip();
        Ellipse2D circle = new Ellipse2D.Double(x, y, size, size);
        g.setClip(circle);

        if (img != null) {
            g.drawImage(img, x, y, size, size, null);
        } else {
            g.setColor(new Color(230, 230, 230));
            g.fillRect(x, y, size, size);
            g.setColor(new Color(170, 170, 170));
            g.setFont(new Font("SansSerif", Font.PLAIN, 14));
            g.drawString("NO IMG", x + 35, y + size / 2);
        }

        g.setClip(oldClip);

        // 얇은 테두리
        g.setColor(new Color(220, 220, 220));
        g.setStroke(new BasicStroke(2f));
        g.draw(circle);
    }

    private static BufferedImage loadLocalImage(HttpServletRequest req, String imgPath) {
        if (imgPath == null || imgPath.isBlank()) return null;

        try {
            String decoded = URLDecoder.decode(imgPath, "UTF-8");

            // 1) realPath 방식
            String realPath = req.getServletContext().getRealPath(decoded);
            if (realPath != null) {
                File f = new File(realPath);
                if (f.exists()) return ImageIO.read(f);
            }

            // 2) getResourceAsStream fallback
            try (InputStream is = req.getServletContext().getResourceAsStream(decoded)) {
                if (is != null) return ImageIO.read(is);
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    // =========================
    // Text Helpers
    // =========================
    private static void drawCenteredText(Graphics2D g, String text, int centerX, int baselineY) {
        if (text == null) text = "";
        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth(text);
        g.drawString(text, centerX - (w / 2), baselineY);
    }

    /**
     * ✅ maxLines 줄까지 "가운데 정렬"로 텍스트를 그린다.
     * - 한국어(공백 없는 경우) 대응: 문자 단위로 줄바꿈
     * - 마지막 줄은 말줄임(...) 처리
     */
    private static void drawCenteredTextMaxLines(Graphics2D g, String text, int centerX, int baselineY, int maxWidth, int maxLines) {
        if (text == null) text = "";
        FontMetrics fm = g.getFontMetrics();

        List<String> lines = wrapByChars(fm, text, maxWidth, maxLines);
        int lineHeight = fm.getHeight();

        // baseline 기준으로 위아래 균형감 있게
        int firstBaseline = baselineY;
        if (lines.size() == 2) {
            firstBaseline = baselineY - (lineHeight / 2) + 2;
        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int w = fm.stringWidth(line);
            g.drawString(line, centerX - (w / 2), firstBaseline + (i * lineHeight));
        }
    }

    private static List<String> wrapByChars(FontMetrics fm, String text, int maxWidth, int maxLines) {
        List<String> out = new ArrayList<>();
        if (text.isBlank()) {
            out.add("");
            return out;
        }

        String remaining = text;

        for (int line = 0; line < maxLines; line++) {
            if (remaining.isEmpty()) break;

            // 마지막 줄이면 말줄임 고려
            boolean lastLine = (line == maxLines - 1);

            String built = "";
            int idx = 0;
            while (idx < remaining.length()) {
                String next = built + remaining.charAt(idx);
                if (fm.stringWidth(next) > maxWidth) break;
                built = next;
                idx++;
            }

            // 한 글자도 못 넣는 극단 상황 방지
            if (built.isEmpty()) {
                built = remaining.substring(0, 1);
                idx = 1;
            }

            remaining = remaining.substring(idx);

            if (lastLine && !remaining.isEmpty()) {
                built = ellipsis(fm, built, maxWidth);
                remaining = ""; // 끝
            }

            out.add(built.trim());
        }

        // 혹시 0줄이면 1줄
        if (out.isEmpty()) out.add(ellipsis(fm, text, maxWidth));
        return out;
    }

    private static String ellipsis(FontMetrics fm, String text, int maxWidth) {
        if (text == null) return "";
        if (fm.stringWidth(text) <= maxWidth) return text;

        String suffix = "...";
        int suffixW = fm.stringWidth(suffix);

        String t = text;
        while (t.length() > 0 && fm.stringWidth(t) + suffixW > maxWidth) {
            t = t.substring(0, t.length() - 1);
        }
        return t + suffix;
    }

    // =========================
    // UI Helpers (pill)
    // =========================
    private static void drawPill(Graphics2D g, int x, int y, String text, Color bg, Color fg) {
        g.setFont(new Font("SansSerif", Font.BOLD, 18));
        FontMetrics fm = g.getFontMetrics();
        int paddingX = 18;
        int paddingY = 10;
        int w = fm.stringWidth(text) + paddingX * 2;
        int h = fm.getHeight() + paddingY;

        g.setColor(bg);
        g.fillRoundRect(x, y, w, h, 999, 999);

        g.setColor(new Color(210, 210, 210));
        g.setStroke(new BasicStroke(1.5f));
        g.drawRoundRect(x, y, w, h, 999, 999);

        g.setColor(fg);
        int textX = x + paddingX;
        int textY = y + (h / 2) + (fm.getAscent() / 2) - 2;
        g.drawString(text, textX, textY);
    }

    // =========================
    // Data Helpers
    // =========================
    private static String sizeLabelKorean(String size, int countFallback) {
        // dto size가 없으면 count로 추정
        if (size == null || size.isBlank()) {
            return countFallback + "가지 맛";
        }
        switch (size) {
            case "A": return "3가지 맛(파인트)";
            case "B": return "4가지 맛(쿼터)";
            case "C": return "5가지 맛(패밀리)";
            case "D": return "6가지 맛(하프갤런)";
            default:  return countFallback + "가지 맛";
        }
    }

    private static String maskWriter(String writer) {
        if (writer == null || writer.isBlank()) return "hac****";
        // 원본 느낌: 뒤를 **** 처리
        if (writer.length() <= 2) return writer + "****";
        return writer.substring(0, Math.min(3, writer.length())) + "****";
    }

    private static String safe(String s) {
        return s == null ? "" : s;
    }
}
