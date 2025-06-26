public class DealerShipDataManager {
    private DataSource dataSource;
    public DealerShipDataManager(DataSource dataSource){
        this.dataSource = dataSource;
    }
//    public List<Product> () {
//        List<Product> products = new ArrayList<>();
//        String sql = "SELECT ProductID " +
//                " , ProductName " +
//                " , UnitPrice " +
//                "FROM products;";
//        Connection conn = dataSource.getConnection();
//        PreparedStatement statement = conn.prepareStatement(sql);
//        ResultSet results = statement.executeQuery(sql);
//        while (results.next())
//        {
//            int id = results.getInt("ProductID");
//            String name = results.getString("ProductName");
//            double price = results.getDouble("UnitPrice");
//            Product product = new Product(id, name, price);
//            products.add(product);
//        }
//        return products;
//    }

}
