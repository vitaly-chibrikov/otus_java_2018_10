package ru.outs.chain;

/**
 * @author sergey
 * created on 11.09.18.
 */
abstract public class ApplProcessor {
    private ApplProcessor next;

    public ApplProcessor getNext() {
        return next;
    }

    public void setNext(ApplProcessor next) {
        this.next = next;
    }

    public void process(Application application) {
        before();
        processInternal(application);
        after();
        if (getNext() != null) {
            getNext().process(application);
        }
    }

    abstract protected void processInternal(Application application);
    abstract public String getProcessorName();

    private void before() {
        System.out.println("before:" + getProcessorName());
    }

    private void after() {
        System.out.println("after:" + getProcessorName());
    }

}
