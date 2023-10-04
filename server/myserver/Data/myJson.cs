namespace myserver.Data;

public class item{
    public int id {get; set;}
    public string username {get; set;}
    public string lat {get; set;}
    public string longitude {get; set;}
    List<string> keywords {get; set;}
    public DateTime created {get; set;}
}

public class rootobj{
    public List<item> items {get; set;}
}

