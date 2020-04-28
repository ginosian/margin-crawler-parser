package com.margin.endpoint;

import com.margin.dto.GenericListResponseDTO;
import com.margin.dto.scheduler.SchedulerJobDTO;
import com.margin.dto.scraper.source.ScraperSourceExtendedDTO;
import com.margin.scheduler.job.api.SchedulerApiClient;
import com.margin.scraper.api.ScraperApiClient;
import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

import static com.cronutils.model.CronType.QUARTZ;

@Controller
@RequestMapping(value = "/internal/ui/v1")
public class IndexEndpointImpl {

    @Autowired
    private SchedulerApiClient schedulerApiClient;

    @Autowired
	private ScraperApiClient scraperApiClient;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model){
		final CronDescriptor descriptor = CronDescriptor.instance(Locale.UK);
		final CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
		final CronParser parser = new CronParser(cronDefinition);
	    final GenericListResponseDTO<SchedulerJobDTO> jobs = schedulerApiClient.scheduler().getAll();
	    final List<SchedulerJobDTO> schedulerJobDTOS = jobs.getItems();
		for (int i = 0; i < schedulerJobDTOS.size(); i++) {
            schedulerJobDTOS.get(i).setJobId(String.valueOf(i));
            schedulerJobDTOS.get(i).setDescription(descriptor.describe(parser.parse(schedulerJobDTOS.get(i).getCronExpression())));
		}
		model.addAttribute("jobs", schedulerJobDTOS);
		final List<ScraperSourceExtendedDTO> sources = scraperApiClient.scraperSource().findAllDetailed().getItems();
		model.addAttribute("sources", sources);
		return "index";
	}
}
