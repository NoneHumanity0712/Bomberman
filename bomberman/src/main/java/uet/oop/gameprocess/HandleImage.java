package uet.oop.gameprocess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import uet.oop.entities.Entity;

public interface HandleImage {
    default Image getImage(String filename, String foldername) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("src/main/resources/" + foldername + "/" + filename);
        Image image = new Image(input);
        image = makeTransparent(image);
        return image;
    }

    default Image makeTransparent(Image inputImage) {
        int W = (int) inputImage.getWidth();
        int H = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(W, H);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (reader.getArgb(x, y) == -65281) {
                    writer.setArgb(x, y, 0);
                } else {
                    writer.setArgb(x, y, reader.getArgb(x, y));
                }
            }
        }
        return outputImage;
    }

    default ImageView createView(Image image, int size, int y, int x) {
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        imageView.setPreserveRatio(true);

        imageView.setX(x);
        imageView.setY(y);

        return imageView;
    }

    default void prepareContext(GraphicsContext context, Canvas canvas) {
        context.setFill(new Color(0, 0, 0, 0));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    default void render(GraphicsContext context, Image image, double x, double y) {
        context.save();

        if (image != null) {
            context.drawImage(image, x * Entity.size,
                    100 + y * Entity.size,
                    image.getWidth() * Entity.SCALE, image.getHeight() * Entity.SCALE);
        }

        context.restore();
    }
}
