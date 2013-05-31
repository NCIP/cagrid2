package org.cagrid.gts.service.impl;

import java.io.InputStream;

import org.projectmobius.common.MobiusConfigurator;
import org.projectmobius.common.MobiusResourceManager;

public class SimpleResourceManager extends MobiusResourceManager{
    public SimpleResourceManager(String file) throws Exception{
            MobiusConfigurator.parseMobiusConfiguration(file,this);
    }
    
    public SimpleResourceManager(InputStream in) throws Exception{
            MobiusConfigurator.parseMobiusConfiguration(in,this);
    }

}