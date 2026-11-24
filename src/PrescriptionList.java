public class PrescriptionList {
    private ListRecord head;

    public PrescriptionList() {
        head = null;
    }

    private class ListRecord {
        public Prescription data;
        public ListRecord next;

        public ListRecord(Prescription pr) {
            data = pr;
            next = null;
        }
    }

    private static boolean comesBefore(Prescription a, Prescription b) {
        return a.getDateOfIssue().compareTo(b.getDateOfIssue())>0;
    }

    public void add(Prescription pr) {
        ListRecord rec = new ListRecord(pr);

        if (head == null || comesBefore(pr, head.data)) {
            rec.next = head;
            head = rec;
            return;
        }

        ListRecord before = head;
        while (before.next != null && comesBefore(before.next.data, pr)) {
            before = before.next;
        }

        rec.next = before.next;
        before.next = rec;
    }
}