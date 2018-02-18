'''
Communicates with pic2recipe server

@author: grandpaa
'''

from selenium import webdriver
import time

address = 'http://tuesday.csail.mit.edu:4242/'

class Im2Ingred:
    
    def __init__(self):
        options = webdriver.ChromeOptions()
        options.add_argument('headless')
        
        self.drv = webdriver.Chrome(chrome_options=options)
        

    def get_info(self, image_path):
        global address
        
        self.drv.get(address)
        input_box = self.drv.find_element_by_id('uploadIm')
        input_box.send_keys(image_path)
        foods = self.drv.find_element_by_id('accordion')
        time.sleep(5)
        foods.find_element_by_id('ui-id-1').click()
        time.sleep(5)
        ingred = foods.find_element_by_id('ui-id-2').find_element_by_class_name('ingrs')
        
        return ingred.text
    
    def close(self):
        self.drv.close()

def __test():
    serv = Im2Ingred()
    ingred = serv.get_info('/home/grandpaa/Downloads/food.jpeg')
    print(ingred)
    serv.close()
    print('done!')
    
__test()
        
        
        
        
        
        