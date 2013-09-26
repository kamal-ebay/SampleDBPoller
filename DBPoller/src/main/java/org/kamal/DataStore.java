package org.kamal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kmuralidharan on 9/25/13.
 */
public class DataStore {
    private static final Map<String, List<JobResult>> resultMap = new HashMap<String, List<JobResult>>();

    private DataStore() {

    }

    public static Map<String, List<JobResult>> getMapInstance() {
        return resultMap;
    }
}
