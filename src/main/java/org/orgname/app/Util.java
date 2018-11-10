package org.orgname.app;

import picocli.CommandLine;

import java.util.Date;

public class Util {

    @CommandLine.Option(names={"-t", "--time"}, description="the msg time")
    public Date time;
    @CommandLine.Option(names={"-m", "--msg"}, description="the msg we want to write to redis", required=true)
    public String msg;

}
