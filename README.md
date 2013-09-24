Overview
-----------

This is the simple diagnostic tool which collects some stats, defined in configuration file,
stores them into DB and show them on a web page. Also it may send a mail message if something is wrong.

Each stat may have normal and error states. This tool detects all changes and may notify you in UI or by e-mail
what is wrong. Then in UI (or directly in DB) you may see the whole history of all stats and that may help you
to diagnose a problem.

For example you may want to define stats like:

* amount of java processes running - from 1 to 5 is normal, other values are error
* amount of disk space remaining - 100GB is normal, less is bad
* delay in opening the homepage - 100ms is good, longer is bad
* etc.

How to run
-------------------
I'm going to make a build script for it :(

What can you do right now is to build java under ``src`` and ``Monitor`` folders and then:

* install MySQL
* execute ``stats.sql``
* run com.actimind.diagnostic.Monitor class - it will run diagnostic tool itself
* make a war from Monitor folder and deploy to Tomcat - it will run UI

UI and diagnostic tool are independent - then may work separately or on different workstations, they just need to share
the DB

How to configure
-------------------
See monitor.xml for reference. Note that you may change it in runtime - diagnostic tool will reload configuration on change.


Notes
------------------
For web UI the simple self-made framework is used on top of JSP. I think usage of Struts could be overkill here.