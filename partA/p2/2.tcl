set ns [new Simulator]
set tf [open 2.tr w]
$ns trace-all $tf
set nf [open 2.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
set n6 [$ns node]

$ns duplex-link $n0 $n2 100Mb 300ms DropTail
$ns duplex-link $n4 $n2 2Mb 300ms DropTail

$ns duplex-link $n5 $n2 100Mb 300ms DropTail
$ns duplex-link $n6 $n2 2Mb 300ms DropTail

$ns duplex-link $n3 $n2 2Mb 300ms DropTail
$ns duplex-link $n1 $n2 2Mb 300ms DropTail

$ns queue-limit $n0 $n2 10
$ns queue-limit $n4 $n2 3

$ns queue-limit $n2 $n6 2
$ns queue-limit $n5 $n2 10

#attach labels
$n0 label "ping0"
$n1 label "ping1"
$n2 label "R1"

$n4 label "ping4"
$n5 label "ping5"
$n6 label "ping6"

set ping0 [new Agent/Ping]
$ns attach-agent $n0 $ping0

set ping4 [new Agent/Ping]
$ns attach-agent $n4 $ping4

set ping5 [new Agent/Ping]
$ns attach-agent $n5 $ping5

set ping6 [new Agent/Ping]
$ns attach-agent $n6 $ping6

$ping0 set packetSize_ 500
$ping0 set interval_ 0.0001

$ping5 set packetSize_ 500
$ping5 set interval_ 0.0001

$ns connect $ping0 $ping4
$ns connect $ping5 $ping6

#flow colors
$ns color 2 blue
$ns color 1 red

$ping0 set class_ 1
$ping5 set class_ 2

Agent/Ping instproc recv {from rtt} {
	$self instvar node_
puts " The node [$node_ id] received an rely from $from with round trip time of $rtt"
}

proc SendPingPacket {} {
	global ns ping0 ping5
	set intervalTime 0.0001
	set now [$ns now]
	$ns at [expr $now+$intervalTime] "$ping0 send"
	$ns at [expr $now+$intervalTime] "$ping5 send"
	$ns at [expr $now+$intervalTime] "SendPingPacket"
}

proc finish {} {
	global ns tf nf
	$ns flush-trace
	close $tf
	close $nf
	exec nam 2.nam &
	exit 0
}
$ns at 0.01 "SendPingPacket"
$ns at 5.0 "finish"
$ns run
