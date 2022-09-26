package uet.oop;

public class Portal extends Entity{
    private boolean activate;

    public Portal(){
        super();
        activate = false;
    }

    public Portal(int x, int y){
        super(x, y);
        activate = false;
    }

    public boolean getPortalState(){
        return activate;
    }

    public void ACTIVATE(){
        activate = true;
    }
}
