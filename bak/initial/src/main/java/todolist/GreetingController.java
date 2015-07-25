package todolist;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class GreetingController {

    //DELETE STUFF START
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    //DELETE STUFF START
    
    //auto inc. counters to assign list and item ids
    private final AtomicInteger list_counter = new AtomicInteger();
    private final AtomicInteger item_counter = new AtomicInteger();
    
    //List and item storage
    private HashMap<Integer, ListData> LISTS = new HashMap<Integer, ListData>();
    private HashMap<Integer, ItemData> ITEMS = new HashMap<Integer, ItemData>();
    
    //Sample data route
    @RequestMapping("/setup")
    public void setInitData(){
        //Clear the lists first
        LISTS.clear();
        ITEMS.clear();
        
        //Add in sample data
        LISTS.put(new Integer(list_counter.incrementAndGet()),
                        new ListData(new Integer(list_counter.get()),"vacation packing"));
        
        ITEMS.put(new Integer(item_counter.incrementAndGet()),
                        new ItemData(new Integer(item_counter.get()),
                                                "pants",false, new Integer(list_counter.get())));
        
        ITEMS.put(new Integer(item_counter.incrementAndGet()),
                        new ItemData(new Integer(item_counter.get()),
                                                "first aid kit",false,new Integer(list_counter.get())));
        
        LISTS.put(new Integer(list_counter.incrementAndGet()),
                        new ListData(new Integer(list_counter.get()),"Afternoon Activities"));
        
        ITEMS.put(new Integer(item_counter.incrementAndGet()),
                        new ItemData(new Integer(item_counter.get()),
                                                "clean eyes",false, new Integer(list_counter.get())));
    
        ITEMS.put(new Integer(item_counter.incrementAndGet()),
                        new ItemData(new Integer(item_counter.get()),
                                                "mow carpet",false, new Integer(list_counter.get())));
    }
    
    /* List routes */
    
    //Fetch all lists
    @RequestMapping("/get_lists")
    public Object[] getLists(){
        //Returns JSONable array of lists
        return LISTS.values().toArray();
    }
    
    //Create list
    @RequestMapping(method=RequestMethod.POST, value="/create_list")
    public Integer createList(@RequestBody HashMap<String,String> new_list){
        LISTS.put(new Integer(list_counter.incrementAndGet()),
            new ListData(
                new Integer(list_counter.get()),new_list.get("name")));
        return new Integer(list_counter.get());
    }
    
    //Delete list
    @RequestMapping(method=RequestMethod.DELETE, value="/delete_list/{list_id}")
    public void deleteList(@PathVariable Integer list_id){
        
        //Remove any items on the list
        Set <Integer> keys = ITEMS.keySet();
        for (Integer key : keys){
            if (ITEMS.get(key).getListId().equals(list_id) ){
                ITEMS.remove(key);
            }
        }
        
        //Remove the list
        LISTS.remove(list_id);
    }
    
    /* item stuff */
    
    //Fetch list items
    @RequestMapping("/list_items/{list_id}")
    public List getListItems(@PathVariable Integer list_id){
        //TODO - use reference array in ListData so wont have to itterate over all items
        
        //return variable ret - ITEMS that match the list_id
        //Set <ItemData> ret = Collections.emptySet();
        List ret = new ArrayList();
        
        //Find items and add to ret
        Set <Integer> keys = ITEMS.keySet();
        for (Integer key : keys){
            if (ITEMS.get(key).getListId().equals(list_id) ){
                ret.add(ITEMS.get(key));
            }
        }
        return ret;
    }
    
    //Create Item
    @RequestMapping(method=RequestMethod.POST, value="/create_item/{list_id}")
    public Integer createItem(@PathVariable Integer list_id, @RequestBody HashMap<String,String> new_item){
        ITEMS.put(new Integer(item_counter.incrementAndGet()),
            new ItemData(new Integer(item_counter.get()),
                                    new_item.get("description"),false,list_id));
        
        return new Integer(item_counter.get());
    }
    
    //DELETE STUFF START
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    //DELETE STUFF END
    
}