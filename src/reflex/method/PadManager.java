package reflex.method;

public class PadManager extends ComputerManager {

    private boolean openCamera(int cameraIndex){
        switch(cameraIndex){
            case 0:
                System.out.println("打开自拍");
                return true;
            case 1:
                System.out.println("打开主摄像头");
                return true;
            default:
                System.out.println("错误");
                return false;
        }
    }

}
