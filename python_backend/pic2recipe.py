'''
Communicates with pic2recipe server

@author: grandpaa
'''

import os
import os.path
from selenium import webdriver
import time

address = 'http://tuesday.csail.mit.edu:4242/'

class Im2Ingred:
    
    def __init__(self):
        options = webdriver.ChromeOptions()
        options.add_argument('headless')
        
        self.drv = webdriver.Chrome(chrome_options=options)
        

    def get_info(self, raw_data):
        global address
        image_path = self._bytes_to_path(raw_data)
        
        self.drv.get(address)
        input_box = self.drv.find_element_by_id('uploadIm')
        input_box.send_keys(image_path)
        foods = self.drv.find_element_by_id('accordion')
        time.sleep(5)
        foods.find_element_by_id('ui-id-1').click()
        time.sleep(5)
        ingred = foods.find_element_by_id('ui-id-2').find_element_by_class_name('ingrs')
        
        return ingred.text
    
    def _bytes_to_path(self, raw_data):
        with open('tmp.jpeg', 'wb') as img_file:
            img_file.write(raw_data)
        return os.path.join(os.getcwd(), 'tmp.jpeg')
    
    def close(self):
        self.drv.close()

      
