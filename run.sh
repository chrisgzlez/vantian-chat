#!/bin/bash
dependencies="/home/chris/usc/3GrEI/codis/vantian-chat/target/classes:/home/chris/.m2/repository/org/postgresql/postgresql/42.7.0/postgresql-42.7.0.jar:/home/chris/.m2/repository/org/checkerframework/checker-qual/3.31.0/checker-qual-3.31.0.jar:/home/chris/.m2/repository/com/formdev/flatlaf/3.2.5/flatlaf-3.2.5.jar:/home/chris/.m2/repository/com/formdev/flatlaf-extras/3.2.5/flatlaf-extras-3.2.5.jar:/home/chris/.m2/repository/com/github/weisj/jsvg/1.2.0/jsvg-1.2.0.jar:/home/chris/.m2/repository/com/formdev/flatlaf-fonts-roboto/2.137/flatlaf-fonts-roboto-2.137.jar:/home/chris/.m2/repository/com/miglayout/miglayout-swing/11.2/miglayout-swing-11.2.jar:/home/chris/.m2/repository/com/miglayout/miglayout-core/11.2/miglayout-core-11.2.jar:/home/chris/.m2/repository/org/swinglabs/swingx/swingx-all/1.6.5-1/swingx-all-1.6.5-1.jar:/home/chris/usc/3GrEI/codis/vantian-chat/src/main/resources/libraries/swing-glasspane-popup-1.2.0.jar:/home/chris/usc/3GrEI/codis/vantian-chat/src/main/resources/libraries/swing-toast-notifications-1.0.1.jar" 
function run_gui() {

    if [[ ! -d "./target/" ]]; then
        echo "No Build Classes found, building..."
        mvn package --quiet || exit 1
        echo "Succesfully build package"
    fi

    /usr/lib/jvm/java-21-openjdk/bin/java \
        -Dfile.encoding=UTF-8 \
        -Dsun.stdout.encoding=UTF-8 \
        -Dsun.stderr.encoding=UTF-8 \
        -classpath ${dependencies} \
        com.vantian.gui.MainWindow
}

function run_client() {
    host="localhost"
    port=5601
    service="vantianchat"
    if [[ -n ${1} ]]; then
        host=${1}
    fi

    if [[ -n ${2} ]]; then
        port=${2}
    fi

    if [[ -n ${3} ]]; then
        service=${3}
    fi

    if [[ ! -d "./target/" ]]; then
        echo "No Build Classes found, building..."
        mvn package --quiet || exit 1
        echo "Succesfully build package"
    fi

    /usr/lib/jvm/java-21-openjdk/bin/java \
        -Dfile.encoding=UTF-8 \
        -Dsun.stdout.encoding=UTF-8 \
        -Dsun.stderr.encoding=UTF-8 \
        -classpath ${dependencies} \
        com.vantian.Client ${host} ${port} ${service} 
}

function run_server() {
    port=5601
    if [[ -n ${2} ]]; then
        port=${2}
    fi

    if [[ ! -d "./target/" ]]; then
        echo "No Build Classes found, building..."
        mvn package --quiet || exit 1
        echo "Succesfully build package"
    fi

    /usr/lib/jvm/java-21-openjdk/bin/java \
        -Dfile.encoding=UTF-8 \
        -Dsun.stdout.encoding=UTF-8 \
        -Dsun.stderr.encoding=UTF-8 \
        -classpath ${dependencies} \
        com.vantian.Server ${port}
}

function build() {
    mvn package
}

function clean() {
    rm -rf target
}
