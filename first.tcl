set ns [new Simulator]

set nf [open 1.nam w]
$ns namtrace-all $nf

set tf [open 1.tr w]
$ns trace-all $tf


set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$ns duplex-link $n0 $n1 100Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail

$ns queue-limit $n0 $n1 10
$ns queue-limit $n1 $n2 10

set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0

set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.0005

$cbr0 attach-agent $udp0

set sink1 [new Agent/Null]
$ns attach-agent $n2 $sink1

$ns connect $udp0 $sink1

proc finish { } {
global ns nf tf
$ns flush-trace

close $nf
close $tf

exec nam 1.nam &
exit 0
}

$ns at 1.0 "$cbr0 start"
$ns at 10.0 "finish"
$ns run
