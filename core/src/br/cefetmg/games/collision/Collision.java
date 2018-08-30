package br.cefetmg.games.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Utilitário para verificação de colisão.
 *
 * @author fegemo <coutinho@decom.cefetmg.br>
 */
public class Collision {

    /**
     * Verifica se dois círculos em 2D estão colidindo.
     * @param c1 círculo 1
     * @param c2 círculo 2
     * @return true se há colisão ou false, do contrário.
     */
    public static final boolean circlesOverlap(Circle c1, Circle c2) {
        Vector2 d = new Vector2(c1.x-c2.x, c1.y-c2.y);
        return c1.radius + c2.radius >= d.len();
    }

    /**
     * Verifica se dois retângulos em 2D estão colidindo.
     * Esta função pode verificar se o eixo X dos dois objetos está colidindo
     * e então se o mesmo ocorre com o eixo Y.
     * @param r1 retângulo 1
     * @param r2 retângulo 2
     * @return true se há colisão ou false, do contrário.
     */
    public static final boolean rectsOverlap(Rectangle r1, Rectangle r2) {
        
        return segmentsOverlap(r1.x, r2.x, r1.x+r1.width, r2.x+r2.width) && segmentsOverlap(r1.y, r2.y, r1.y+r1.height, r2.y+r2.height);
    }
    private static boolean segmentsOverlap(float aMin, float bMin, float aMax, float bMax){        
        return aMax>=bMin && aMin<=bMax;
    }
    public static final boolean rectCircleOverlap(Circle c, Rectangle r) {
        Vector2 rectangleCenter = new Vector2();
        r.getCenter(rectangleCenter);
        Vector2 d = new Vector2(c.x, c.y).sub(rectangleCenter);
        Vector2 dx = new Vector2(d.x, 0);
        Vector2 dy = new Vector2(0, d.y);
        dx = dx.clamp(0, r.width/2); dy = dy.clamp(0, r.height/2);
        Vector2 p = new Vector2(rectangleCenter).add(dx.add(dy));
        return Math.pow(c.x-p.x, 2) + Math.pow(c.y-p.y, 2) <= Math.pow(c.radius,2);
    }
}
