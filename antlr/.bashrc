# Sample .bashrc for SuSE Linux
# Copyright (c) SuSE GmbH Nuernberg

# There are 3 different types of shells in bash: the login shell, normal shell
# and interactive shell. Login shells read ~/.profile and interactive shells
# read ~/.bashrc; in our setup, /etc/profile sources ~/.bashrc - thus all
# settings made here will also take effect in a login shell.
#
# NOTE: It is recommended to make language settings in ~/.profile rather than
# here, since multilingual X sessions would not work properly if LANG is over-
# ridden in every subshell.

# Some applications read the EDITOR variable to determine your favourite text
# editor. So uncomment the line below and enter the editor of your choice :-)
#export EDITOR=/usr/bin/vim
#export EDITOR=/usr/bin/mcedit

# For some news readers it makes sense to specify the NEWSSERVER variable here
#export NEWSSERVER=your.news.server

# If you want to use a Palm device with Linux, uncomment the two lines below.
# For some (older) Palm Pilots, you might need to set a lower baud rate
# e.g. 57600 or 38400; lowest is 9600 (very slow!)
#
#export PILOTPORT=/dev/pilot
#export PILOTRATE=115200

test -s ~/.alias && . ~/.alias || true
alias ll='ls -alF'
alias ide='sh ~/opt/idea-IU-135.909/bin/idea.sh'
alias sblm='sublime'
alias antlr4='java -jar /usr/local/lib/antlr-4.4.jar'
alias grun="java -cp '.:/usr/local/lib/antlr-4.4.jar' org.antlr.v4.runtime.misc.TestRig"
export JAVA_HOME=~/opt/jdk1.8.0_05
export M2_HOME=~/opt/apache-maven-3.2.3
export GRADLE_HOME=~/opt/gradle-2.1
PATH=$PATH:$JAVA_HOME/bin:$M2_HOME/bin:$GRADLE_HOME/bin


