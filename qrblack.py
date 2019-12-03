import pyqrcode
import os
import cv2
import numpy
'''def main():
	#f = open("outputjava.dat","r")
	#line=f.read()
	line1 = "Hello"
	qr1 = pyqrcode.create(line1, error = 'H', mode = 'binary')
	qr1.png('1.png', scale = 8, module_color = [255,42,0,255])
	line2 = "World"
	qr2 = pyqrcode.create(line2, error = 'H', mode = 'binary')
	qr2.png('2.png', scale = 8, module_color = [255,170,0,255])
	line3 = "Beta"
	qr3 = pyqrcode.create(line3, error = 'H', mode = 'binary')
	qr3.png('3.png', scale = 8, module_color = [187,255,0,255])
	#f.close()
if __name__ == "__main__":
    main()
'''
def main():
	f = open("input.txt","rb")
	line=f.read()
	line=line.lower()
	qr1 = pyqrcode.create(line, error = 'H', mode = 'binary')
	qr1.png('black.png', scale = 6, module_color = [0,0,0,255])
	#f.close()
if __name__ == "__main__":
    main()
