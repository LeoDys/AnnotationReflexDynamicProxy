package reflex.method;

public class ComputerManager {

    public void open() {
        System.out.println("开机");
    }

    private void standBy() {
        System.out.println("待机");
    }

    private String getCpuStatus(int cpuCoreIndex) {
        return "第" + cpuCoreIndex + "个CPU正在满载状态工作";
    }

}
