a=$(ps -ef | grep tsinghua_device_tcpprotocol-0.0.1-SNAPSHOT.jar | grep -v grep |grep -v bash | awk '{ print $2 }')
wait
if [ -n "$a" ];then
        echo "stopping platfrom($a)"
        kill -9 $a
        sleep 2
else
        echo "middleWare is not running";
wait
fi