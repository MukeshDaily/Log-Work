package logwork;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WorkController {
    WorkDatabase wdata = new WorkDatabase();
		
	@RequestMapping(value="/maketable", method=RequestMethod.GET)
    public @ResponseBody String makeTable() {
		wdata.makeTable();
        return "worklogs table created";
    }
	
    @RequestMapping(value="/postwork", method=RequestMethod.GET)
    public String workForm(Model model) {
        model.addAttribute("work", new Work());
        return "postwork";
    }

    @RequestMapping(value="/postwork", method=RequestMethod.POST)
    public String workSubmit(@ModelAttribute Work work, Model model) {
        model.addAttribute("work", work);		
		wdata.insertData(work);		
        return "result";
    }

	@RequestMapping(value="/printwork", method=RequestMethod.GET)
    public @ResponseBody String listWork() {        
		wdata.printData();		
        return "Retrieved data are listed in console";
    }	
			
	@RequestMapping(value="/listwork", method=RequestMethod.GET)
    public String listWork(Model model) {        
		List<DataHolder> results = wdata.retrieveData();			
			
		model.addAttribute("dataholder", results);		
        return "listwork";
    }	
}
