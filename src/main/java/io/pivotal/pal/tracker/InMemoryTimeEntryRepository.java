package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> timeEntryMap = new HashMap<>();
    private Long timeEntryId = 1L;


    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(timeEntryId);
        timeEntryMap.put(timeEntryId, timeEntry);
        timeEntryId++;
        return timeEntry;
    }

    public TimeEntry find(long id) {


        return timeEntryMap.get(id);
    }

    public List<TimeEntry> list() {
        return timeEntryMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {

         if (find(id) == null)
         {
             return null;
         }
         else
         {
             TimeEntry timeEntryUpdated = new TimeEntry(id,
                     timeEntry.getProjectId(),
                     timeEntry.getUserId(),
                     timeEntry.getDate(),
                     timeEntry.getHours());

             timeEntryMap.put(id, timeEntryUpdated);
             return timeEntryUpdated;
         }
    }

    public void delete(long id) {

        timeEntryMap.remove(id);


    }
}
