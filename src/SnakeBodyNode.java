public class SnakeBodyNode {
    Node head;
    class Node{
        BoardPoint position;
        Node next;

        Node(BoardPoint p){
            position = new BoardPoint(p.getRow(), p.getCol());
            next = null;
        }
    }

    public SnakeBodyNode add(SnakeBodyNode list,BoardPoint point){
        Node newNode = new Node(point);

        if(list.head == null)
            list.head = newNode;
        else{
            Node last = list.head;
            while(last.next != null){
                last = last.next;
            }
            last.next = newNode;
        }
        return list;
    }
}
