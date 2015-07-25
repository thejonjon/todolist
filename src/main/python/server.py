#!/usr/bin/python
import sys, os, logging, socket

#Windows service cannot handle vevent apparenlty
import simplejson as json
import bottle
from bottle import template,request
from bottle import Bottle

pythonserver = Bottle()

"""
Quote unquote "DB" functions
Normally would be in a seperate module, but i'll append them with _ instaed
"""

def _list_create(fields):
    #field, dict
    
    #Lets get a new ID for the list
    new_id = len(LISTS.keys())
    
    #LISTS[new_id] = {} #reset the ID if it exists? never should happen
    #Creat a new list item
    LISTS.setdefault(new_id,fields)
    return new_id
    
def _list_update(id,fields):
    LISTS[id].update(fields)
    
def _list_delete(id):
    #id, int: of list to delete.
    
    #Make sure to clear items "table" of associated records
    for k,v in ITEMS.items():
        if v['list_id'] == id:
            del ITEMS[k]
    del LISTS[id]

def _list_list():
    ret = []
    for k,v in LISTS:
        ret.append(v.update('id':k))
    return ret

def _item_create(list_id,fields):
    new_id = len(ITEMS.keys())
    ITEMS[new_id] = fields
    ITEMS[new_id]['list_id'] = list_id
    return new_id

def _item_update(id,fields):
    ITEMS[id].update(fields)
    
def _item_delete(id):
    del ITEMS[id]
    
def _item_search(list_id):
    #Search for items by list id
    ret = []
    for k,v in ITEMS
        if v['list_id'] == list_id:
            ret.append(ITEMS[k])
    return ret

"""
Exposed HTTP functions
I like to make them easily readable and sepearted by functionality
"""

@pythonserver.get('/static/<file:path>')
def static(file):
        return sflcapp.static_file(file,root=sys.path[0]+'/static')

@pythonserver.route('/<name:re:.*\.(\w){2,4}$>')
@pythonserver.route('/<name:re:.*\.(\w){2,4}\?\_dc=.*>')
def catch_all_static(name):
    return static(name)
    
@pythonserver.post('/list/create')
def list_create():
    #Expects JSON {'description':string}
    #Returns JSON {'id':new_list_id}
    pass
    
@pythonserver.put('/list/update/<list_id>')
def list_update(list_id):
    #Expects JSON {'description':string}
    #Returns Nothing (200 unless error)
    pass
    
@pythonserver.delete('/list/delete/<list_id>')
def list_delete(list_id):
    #Expects Nothing
    #Returns Nothing (200 unless error)
    pass
    
@pythonserver.post('/item/create/<list_id>')
def item_create(list_id):
    #Expects JSON: {'description':string}
    #Assumes 'completed' status is false by default
    #Returns JSON {'id':new_item_id}
    pass
    
@pythonserver.put('/item/update/<item_id>')
def item_update(item_id):
    #Expects JSON: field like "description" (string) or "completed" (bool)
    #Returns Nothing (200 unless error)
    pass
    
@pythonserver.delete('/item/delete/<item_id>')
def item_delete(item_id):
    pass
    
if __name__ =='__main__':
    pythonserver.run(host='localhost', port=8081, server='wsgiref',debug=True)
