package routecalculation;

public class testDijk {
    public static void main(String [] args) {
        flight[] flights = {
                new flight(0, 2, 1),
                new flight(0, 3, 4),
                new flight(0, 4, 2),
                new flight(0, 1, 3),
                new flight(1, 3, 2),
                new flight(1, 4, 3),
                new flight(1, 5, 1),
                new flight(2, 4, 1),
                new flight(3, 5, 4),
                new flight(4, 5, 2),
                new flight(4, 6, 7),
                new flight(4, 7, 2),
                new flight(5, 6, 4),
                new flight(6, 7, 5)
        };
        int source = 0;
        Grapher g = new Grapher(flights);
        g.calculateShortestDistance(source);
        g.printResult(); // let's try it !
    }
}
