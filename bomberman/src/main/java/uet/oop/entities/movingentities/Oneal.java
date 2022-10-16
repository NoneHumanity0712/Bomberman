package uet.oop.entities.movingentities;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Oneal extends Enemy{

    Image[][] oneal_images;
    Image oneal_dead;

    public Oneal() throws FileNotFoundException {
        super();
        super.setType('2');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(0.75);
        setStep(getSpeed()/8);
        setStepCount(0);

        oneal_images = new Image[2][3];
        setupImage();
        setImage(oneal_images[0][getStepCount() % 2]);
    }

    public Oneal(int x, int y) throws FileNotFoundException {
        super(x, y);
        super.setType('2');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(0.75);
        setStep(getSpeed()/8);
        setStepCount(0);

        oneal_images = new Image[2][3];
        setupImage();
        setImage(oneal_images[0][getStepCount() % 2]);
    }

    public Oneal(Oneal oneal) throws FileNotFoundException {
        super(oneal);
        super.setType('2');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(0.75);
        setStep(getSpeed()/8);
        setStepCount(0);

        oneal_images = new Image[2][3];
        setupImage();
        setImage(oneal_images[0][getStepCount() % 2]);
    }
    
    @Override
    public void setupImage() throws FileNotFoundException {
        for (int i = 0; i < 3; i++) {
            oneal_images[0][i] = getImage("sprites/oneal_right" + i + ".png");
        }
    }
    

    @Override
    public void DEAD() {
        // TODO Auto-generated method stub
        super.DEAD();
    }

    @Override
    public void MOVE_DOWN() {
        // TODO Auto-generated method stub
        super.MOVE_DOWN();
    }

    @Override
    public void MOVE_LEFT() {
        // TODO Auto-generated method stub
        super.MOVE_LEFT();
    }

    @Override
    public void MOVE_RIGHT() {
        // TODO Auto-generated method stub
        super.MOVE_RIGHT();
    }

    @Override
    public void MOVE_UP() {
        // TODO Auto-generated method stub
        super.MOVE_UP();
    }

    
    
}
