package unittests.renderer;

import renderer.*;
import org.junit.jupiter.api.Test;
import primitives.Color;

import static primitives.Util.*;

//import static org.junit.jupiter.api.Assertions.*;


/**
 * test image writing
 *
 * @author Hadass 
 * @author 
 */
class ImageWriterTests {

    /**
     * test
     */
    @Test
    public void testImageWriter() {

        ImageWriter image = new ImageWriter("image", 800, 500); //תמונה חדשה
        for (int j = 0; j < 800; j++) { //לולאה שעוברת על כל הפיקסלים
            if (isZero(j % 50)) //אם שארית החלוקה של מספר הפיקסל היא 0, צובע אותו בשחור
                for (int i = 0; i < 500; i++)
                    image.writePixel(j, i, Color.BLACK);
            else
                for (int i = 0; i < 500; i++) {
                    if (isZero(i % 50))
                        image.writePixel(i, i, Color.BLACK);
                    else
                        image.writePixel(j, i, new Color(248, 24, 148));    //אם זה לא פיקסל מהרשת, צובע אותו בורוד
                }
        }
        image.writeToImage();
    }
}