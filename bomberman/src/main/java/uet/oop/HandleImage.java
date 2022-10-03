package uet.oop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public interface HandleImage {
    default Image getImage(String path) throws FileNotFoundException{
        FileInputStream input = new FileInputStream(path);
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
                if (reader.getArgb(x, y) == -65281){
                    writer.setArgb(x, y, 0);
                } else {
                    writer.setArgb(x, y, reader.getArgb(x, y));
                }
            }
        }
        return outputImage;
    }

    default ImageView createView (Image image, int size, int y, int x){
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        imageView.setPreserveRatio(true);

        imageView.setX(x);
        imageView.setY(y);

        return imageView;
    }
}
