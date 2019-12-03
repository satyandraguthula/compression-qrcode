import pyqrcode
import os
import cv2
import numpy
import socket
import subprocess
'''
def main():
	#f = open("outputjava.dat","r")
	#line=f.read()
	f = open("outputjava.txt","rb")
	line=f.read()
	line=line.lower()
	size=len(line)
	f = open("outputjava.txt","rb")
	line1=""
	line2=""
	line3=""
	read=0
	for i in range(0,size/3,1):
		line1=line1+f.read(1)
		read+=1
	for i in range(0,size/3,1):
		line2=line2+f.read(1)
		read+=1
	for read in range(size):
		line3=line3+f.read(1)
		read+=1
	f.close()
	f = open("hue0.txt","w")
	f.write(line1)
	f.close
	f = open("hue1.txt","w")
	f.write(line2)
	f.close
	f = open("hue2.txt","w")
	f.write(line3)
	f.close
	qr1 = pyqrcode.create(line1, error = 'H', mode = 'binary')
	qr1.png('1.png', scale = 6, module_color = [255,42,0,255])
	qr2 = pyqrcode.create(line2, error = 'H', mode = 'binary')
	qr2.png('2.png', scale = 6, module_color = [255,128,0,255])
	qr3 = pyqrcode.create(line3, error = 'H', mode = 'binary')
	qr3.png('3.png', scale = 6, module_color = [255,213,0,255])
	subprocess.call(["sudo python qropencv.py"],shell=True)
	#f.close()
'''
def main():
	f = open("outputjava.txt","rb")
	line=f.read()
	line=line.lower()
	size=len(line)
	f = open("outputjava.txt","rb")
	line1=""
	line2=""
	line3=""
	line4=""
	read=0
	for i in range(0,size/4,1):
		line1=line1+f.read(1)
		read+=1
	for i in range(0,size/4,1):
		line2=line2+f.read(1)
		read+=1
	for i in range(0,size/4,1):
		line3=line3+f.read(1)
		read+=1
	for read in range(size):
		line4=line4+f.read(1)
		read+=1
	f = open("hue0.txt","w")
	f.write(line1)
	f.close
	f = open("hue1.txt","w")
	f.write(line2)
	f.close
	f = open("hue2.txt","w")
	f.write(line3)
	f.close
	f = open("hue3.txt","w")
	f.write(line4)
	f.close
	qr1 = pyqrcode.create(line1, error = 'H', mode = 'binary')
	qr1.png('1.png', scale = 6, module_color = [0,255,170,255])
	qr2 = pyqrcode.create(line2, error = 'H', mode = 'binary')
	qr2.png('2.png', scale = 6, module_color = [0,255,255,255])
	qr3 = pyqrcode.create(line3, error = 'H', mode = 'binary')
	qr3.png('3.png', scale = 6, module_color = [0,170,255,255])
	qr4 = pyqrcode.create(line4, error = 'H', mode = 'binary')
	qr4.png('4.png', scale = 6, module_color = [128,0,255,255])
	print "Created QR codes. Now merging"
	subprocess.call(["sudo python qropencv.py"],shell=True)
	#f.close()
if __name__ == "__main__":
    main()
