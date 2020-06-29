package models.aftertrigger;

public abstract class AfterTrigger {
    private AfterTrigger next;

    public AfterTrigger linkWith(AfterTrigger next) {
        this.next = next;
        return next;
    }

    public abstract void execute(AfterTriggerRequest request);

    protected void checkNext(AfterTriggerRequest request) {
        if (next != null) {
         next.execute(request);
        }
    }
}